class DummyClass_91579 {
    @Test
    public void testyy() throws InterruptedException {
        long wallClock = System.currentTimeMillis();

        HashMap<Integer, byte[]> map = Maps.newHashMap();
        for (int i = 0; i < 10000000; i++) {
            byte[] a = new byte[100];
            map.put(i, a);
        }

        System.out.println("Time Consumed: " + (System.currentTimeMillis() - wallClock));
    }

}