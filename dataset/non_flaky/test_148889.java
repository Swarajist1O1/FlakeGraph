class DummyClass_148889 {
    @Test
    public void EntityTests_GeoCoordinatesSerializationDeserializationTest() {
        GeoCoordinates geoCoordinates = new GeoCoordinates();
        geoCoordinates.setLatitude(22.00);
        geoCoordinates.setLongitude(23.00);

        Assert.assertEquals("GeoCoordinates", geoCoordinates.getType());

        Entity deserializedEntity = new Entity().setAs(geoCoordinates);
        Assert.assertEquals(deserializedEntity.getType(), geoCoordinates.getType());

        GeoCoordinates geoDeserialized = deserializedEntity.getAs(GeoCoordinates.class);
        Assert.assertEquals(geoCoordinates.getType(), geoDeserialized.getType());
        Assert.assertEquals(
            geoCoordinates.getLatitude(), geoDeserialized.getLatitude(), Double.MAX_VALUE
        );
        Assert.assertEquals(
            geoCoordinates.getLongitude(), geoDeserialized.getLongitude(), Double.MAX_VALUE
        );
    }

}