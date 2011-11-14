/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.server.smack.core;

import com.lmax.disruptor.WorkHandler;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class SerializationHandler implements WorkHandler<ResponseEvent> {
    
    public void onEvent(final ResponseEvent event)
            throws Exception {
        Channel out = event.getOutputChannel();
        
        HttpResponseStatus status = event.getInvocationResponse().getStatus();
        HttpResponse response = new DefaultHttpResponse(HTTP_1_1, status);
        
        if(status == HttpResponseStatus.CREATED) {
            response.addHeader(HttpHeaders.Names.LOCATION, event.getInvocationResponse().getLocation());
        }
        
        response.addHeader(HttpHeaders.Names.CONTENT_LENGTH, 0);

        out.write(response);
    }
}
