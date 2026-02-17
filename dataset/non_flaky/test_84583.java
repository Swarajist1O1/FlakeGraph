class DummyClass_84583 {
    @AfterEach
    public void tearDown() throws Exception {
        if (nodes != null) {
            for (int i = 0; i < nodes.length; i++) {
                WriteLock node = nodes[i];
                if (node != null) {
                    System.out.println("Closing node: " + i);
                    node.close();
                    if (workAroundClosingLastZNodeFails && i == nodes.length - 1) {
                        System.out.println("Not closing zookeeper: " + i + " due to bug!");
                    } else {
                        System.out.println("Closing zookeeper: " + i);
                        node.getZookeeper().close();
                        System.out.println("Closed zookeeper: " + i);
                    }
                }
            }
        }
        System.out.println("Now lets stop the server");
        super.tearDown();

    }

}