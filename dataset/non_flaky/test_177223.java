class DummyClass_177223 {
    @Test
        public int compare(Endpoint o1, Endpoint o2) {
            if (o1.equals(o2) && o1.weight() == o2.weight()) {
                return 0;
            }
            return -1;
        }

}