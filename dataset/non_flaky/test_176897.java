class DummyClass_176897 {
  @Test
  public void testKeyOnly() {
    ObjObjMap<String,String> map = HashObjObjMaps.newMutableMap(
        new String[]{"foo", "bar", "baz"},
        new String[]{"1", "3", "4"}
    );
    map.removeIf(new KeyOnlyBiPredicate<String, String>(new Predicate<String>() {
      @Override
      public boolean test(String s) {
        return s.startsWith("b");
      }

}