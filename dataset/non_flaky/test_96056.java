class DummyClass_96056 {
  @Test
  public void testParseArray() {
    String in = "{Chess,is,not,a,predominantly,physical,sport,\"\",\"\",yet,neither,are,shooting,and,curling,-LRB-,which,\"\",\"\",in,fact,\"\",\"\",has,been,nicknamed,``,chess,on,ice,'',5,-RRB-,.}";
    String[] out = {"Chess","is","not","a","predominantly","physical","sport",",","yet","neither","are","shooting","and","curling","-LRB-","which",",","in","fact",",","has","been","nicknamed","``","chess","on","ice","''","5","-RRB-","."};

    // System.err.println(in);
    // System.err.println(Arrays.asList(out));
    // System.err.println(Arrays.asList(TSVUtils.parseArray(in).toArray()));
    Assert.assertArrayEquals(out, TSVUtils.parseArray(in).toArray());

    String in2 = "{Chess,is,not,a,predominantly,physical,sport,\"\",\"\",yet,neither,are,shooting,and,curling,(,which,\"\",\"\",in,fact,\"\",\"\",has,been,nicknamed,``,chess,on,ice,'',5,),.}";
    String[] out2 = {"Chess","is","not","a","predominantly","physical","sport",",","yet","neither","are","shooting","and","curling","(","which",",","in","fact",",","has","been","nicknamed","``","chess","on","ice","''","5",")","."};

    Assert.assertArrayEquals(out2, TSVUtils.parseArray(in2).toArray());
  }

}