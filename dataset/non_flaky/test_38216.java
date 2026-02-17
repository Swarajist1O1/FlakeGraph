class DummyClass_38216 {
    @Test
    public void testProperCaseWord() throws Exception {
        String[] words = new String[] { "AA102", "nw", "dog", "daVID CHiu", "yu-gi-oh rules" };
        String[] results = new String[] { "AA102", "Nw", "Dog", "David chiu", "Yu-gi-oh rules" };
        for (int i=0; i < words.length; i++) {
            String result = TextUtils.properCaseWord(words[i]);
            assertEquals(results[i], result);
        }
    }

}