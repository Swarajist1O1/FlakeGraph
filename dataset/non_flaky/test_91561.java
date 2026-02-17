class DummyClass_91561 {
    @Test
    public void testRollback() throws Exception {
        ResourceStore store = ResourceStore.getStore(KylinConfig.getInstanceFromEnv());
        byte[] bytes = new byte[] { 0, 1, 2 };
        RawResource raw;
        Checkpoint cp;

        cp = store.checkpoint();
        try {
            store.putResource("/res1", new StringEntity("data1"), 1000, StringEntity.serializer);
        } finally {
            cp.close();
        }
        StringEntity str = store.getResource("/res1", StringEntity.serializer);
        assertEquals("data1", str.toString());

        cp = store.checkpoint();
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            store.putResource("/res2", is, 2000);
            is.close();
            
            store.putResource("/res1", str, 2000, StringEntity.serializer);
            store.deleteResource("/res1");

            assertEquals(null, store.getResource("/res1"));
            assertEquals(2000, (raw = store.getResource("/res2")).lastModified());
            raw.content().close();
            
            cp.rollback();
            
            assertEquals(null, store.getResource("/res2"));
            assertEquals(1000, (raw = store.getResource("/res1")).lastModified());
            raw.content().close();
        } finally {
            cp.close();
        }
    }

}