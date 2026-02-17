class DummyClass_91583 {
    @Test
    public void testStringSplit() throws Exception {

        String[] origin = new String[] { "ab,c", "cd|e" };

        // test with sequence file default delimiter
        String delimiter = "\01"; //"\u001F"; "\t";
        String concated = StringUtils.join(Arrays.asList(origin), delimiter);
        System.out.println(concated);

        String[] newValues = concated.split(delimiter);
        Assert.assertEquals(origin, newValues);

        newValues = concated.split("\\" + delimiter);
        Assert.assertEquals(origin, newValues);
    }

}