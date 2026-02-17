class DummyClass_156093 {
  @Test
  public void testInner() {
    NopStmt[] nops = new NopStmt[6];
    for (int i = 0; i < nops.length; i++) {
      nops[i] = Jimple.v().newNopStmt();
    }
    UnitPatchingChain chainNew = new UnitPatchingChain(new HashChain<Unit>());
    UnitContainer container = new UnitContainer(nops[0], new UnitContainer(nops[1], new UnitContainer(nops[2]), nops[3]),
        nops[4], new UnitContainer(nops[5]));
    AsmMethodSource.emitUnits(container, chainNew);
    UnitPatchingChain chainOld = new UnitPatchingChain(new HashChain<Unit>());
    oldEmitImplementation(container, chainOld);

    Assert.assertEquals(chainOld.size(), chainNew.size());
    Iterator<Unit> itO = chainOld.iterator();
    Iterator<Unit> itN = chainNew.iterator();
    while (itO.hasNext()) {
      Unit oo = itO.next();
      Unit nn = itN.next();
      if (oo != nn) {
        Assert.fail();
      }
    }
  }

}