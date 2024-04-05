package org.plugins;
import org.apache.activemq.artemis.api.core.ActiveMQException;
import org.apache.activemq.artemis.api.core.Message;
import org.apache.activemq.artemis.core.server.ServerSession;
import org.apache.activemq.artemis.core.server.plugin.ActiveMQServerPlugin;
import org.apache.activemq.artemis.core.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AMQLargeMessageCounter implements ActiveMQServerPlugin {
    private static Logger logger = LoggerFactory.getLogger(AMQLargeMessageCounter.class);
    @Override
    public void beforeSend(ServerSession session, Transaction tx, Message message, boolean direct, boolean noAutoCreateQueue) throws ActiveMQException {
        if (message.isLargeMessage()) {
            logger.info("Large message found: "+ message.getPersistentSize() + " from host "+ session.getRemotingConnection().getRemoteAddress());
        }
        ActiveMQServerPlugin.super.beforeSend(session, tx, message, direct, noAutoCreateQueue);
    }
}
