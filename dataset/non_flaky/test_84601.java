class DummyClass_84601 {
    @Test
    public void testSimpleCase() throws Exception {
        configureServers(serverCount);
        configureClients(clientCount, SimpleClient.class, getHostPort());
        Stat stat = new Stat();
        startServers();
        LOG.debug("Connecting to " + getHostPort());
        ZooKeeper zk = new ZooKeeper(getHostPort(), 15000, this);
        waitForConnect(zk, 10000);
        zk.create("/simpleCase", "orig".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        startClients();

        // Check that all clients connect properly
        for(int i = 0; i < getClientCount(); i++) {
            for(int j = 0; j < maxTries; j++) {
                try {
                    byte b[] = zk.getData("/simpleCase/" + i, false, stat);
                    Assert.assertEquals("orig", new String(b));
                } catch(NoNodeException e) {
                    if (j+1 == maxTries) {
                        Assert.fail("Max tries exceeded on client " + i);
                    }
                    Thread.sleep(1000);
                }
            }
        }

        // Kill half the servers, make a change, restart the dead
        // servers, and then bounce the other servers one by one
        for(int i = 0; i < getServerCount(); i++) {
            stopServer(i);
            if (i+1 > getServerCount()/2) {
                startServer(i);
            } else if (i+1 == getServerCount()/2) {
                Assert.assertTrue("Connection didn't recover", waitForConnect(zk, 10000));
                try {
                    zk.setData("/simpleCase", "new".getBytes(), -1);
                } catch(ConnectionLossException e) {
                    Assert.assertTrue("Connection didn't recover", waitForConnect(zk, 10000));
                    zk.setData("/simpleCase", "new".getBytes(), -1);
                }
                for(int j = 0; j < i; j++) {
                    LOG.info("Starting server " + j);
                    startServer(i);
                }
            }
        }
        Thread.sleep(100); // wait for things to stabilize
        Assert.assertTrue("Servers didn't bounce", waitForConnect(zk, 15000));
        try {
            zk.getData("/simpleCase", false, stat);
        } catch(ConnectionLossException e) {
            Assert.assertTrue("Servers didn't bounce", waitForConnect(zk, 15000));
        }

        // check that the change has propagated to everyone
        for(int i = 0; i < getClientCount(); i++) {
            for(int j = 0; j < maxTries; j++) {
                byte[] data = zk.getData("/simpleCase/" + i, false, stat);
                if (new String(data).equals("new")) {
                    break;
                }
                if (j+1 == maxTries) {
                    Assert.fail("max tries exceeded for " + i);
                }
                Thread.sleep(1000);
            }
        }

        // send out the kill signal
        zk.setData("/simpleCase", "die".getBytes(), -1);

        // watch for everyone to die
        for(int i = 0; i < getClientCount(); i++) {
            try {
                for(int j = 0; j < maxTries; j++) {
                    zk.getData("/simpleCase/" + i, false, stat);
                    if (j+1 == maxTries) {
                        Assert.fail("max tries exceeded waiting for child " + i + " to die");
                    }
                    Thread.sleep(200);
                }
            } catch(NoNodeException e) {
                // Great this is what we were hoping for!
            }
        }

        stopClients();
        stopServers();
    }

}