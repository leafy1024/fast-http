package org.neo4j.smack.serialization.strategy;

import org.neo4j.graphdb.Node;
import org.neo4j.smack.serialization.AbstractNonStreamingSerializationStrategy;
import org.neo4j.smack.serialization.SerializationException;
import org.neo4j.smack.serialization.Serializer;

/**
 * @author mh
 * @since 27.11.11
 */
public class NodeSerializationStrategy extends AbstractNonStreamingSerializationStrategy<Node> {

    @Override
    public void serialize(Node node, Serializer out) throws SerializationException {
        out.putNode(node);
    }
}