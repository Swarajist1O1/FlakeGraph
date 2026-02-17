class DummyClass_38224 {
    @Test
    public void testMaximalPrefix() throws Exception{
        ArrayList<String> list1 = new ArrayList<String>(7);
        list1.add("abcdef");
        list1.add("abcdefg");
        list1.add("abcdefgh");
        list1.add("abcd");
        list1.add("abcdefl58a");
        list1.add("abcdeeeeee");
        list1.add("abcde888");
        assertEquals("Wrong maximal prefix","abcd",TextUtils.findMaximalPrefix(list1));

        list1.clear();
        assertEquals("Should be empty string","",TextUtils.findMaximalPrefix(list1));
        assertEquals("Should be empty string","",TextUtils.findMaximalPrefix(null));

        list1.add("abcd");
        assertEquals("Should be abcd","abcd",TextUtils.findMaximalPrefix(list1));
        list1.add("efgh");
        list1.add("ifht");

        assertEquals("Should be empty string","",TextUtils.findMaximalPrefix(list1));
    }

}