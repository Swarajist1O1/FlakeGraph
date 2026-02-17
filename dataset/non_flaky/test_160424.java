class DummyClass_160424 {
  @TestTemplate
  public void setup(SpecContext specContext) {
    spec = specContext.getSpec();
    dataStructureUtil = specContext.getDataStructureUtil();
    schemaProvider = new SchemaObjectProvider(spec);
    provider = new ValidatorDataProvider(spec, validatorApiChannel, combinedChainDataClient);
    blockInternal = dataStructureUtil.randomBeaconBlock(123);
    block = schemaProvider.getBeaconBlock(blockInternal);
  }

}