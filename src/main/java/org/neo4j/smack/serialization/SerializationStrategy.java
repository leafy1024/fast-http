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
package org.neo4j.smack.serialization;

public interface SerializationStrategy<T> {

    public static SerializationStrategy<Object> NO_OP = new SerializationStrategy<Object>() {
        public void serialize(Object value, Serializer out) throws SerializationException
        {

        }

        public boolean isStreaming()
        {
            return false;
        }

        public int estimatedSerializedSizeInBytes(Object value)
        {
            return 0;
        }
    };

    void serialize(T value, Serializer out)
            throws SerializationException;

    boolean isStreaming();

    // To be reconsidered, currently used to set initial size of output buffer
    int estimatedSerializedSizeInBytes(T value);

}