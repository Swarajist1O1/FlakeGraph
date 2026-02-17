class DummyClass_148891 {
    @Test
    public void EntityTests_PlaceSerializationDeserializationTest() {
        Place placeEntity = new Place();
        placeEntity.setName("TESTTEST");

        Assert.assertEquals("Place", placeEntity.getType());

        Entity deserializedEntity = new Entity().setAs(placeEntity);
        Assert.assertEquals(deserializedEntity.getType(), placeEntity.getType());

        Place placeDeserialized = deserializedEntity.getAs(Place.class);
        Assert.assertEquals(placeEntity.getType(), placeDeserialized.getType());
    }

}