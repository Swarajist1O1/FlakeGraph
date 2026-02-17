class DummyClass_162424 {
    @Test
    public void simpleMongoDbTest() {
        MongoClient mongoClient = new MongoClient(mongo.getContainerIpAddress(), mongo.getMappedPort(MONGO_PORT));
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("testCollection");

        Document doc = new Document("name", "foo")
                .append("value", 1);
        collection.insertOne(doc);

        Document doc2 = collection.find(new Document("name", "foo")).first();
        assertEquals("A record can be inserted into and retrieved from MongoDB", 1, doc2.get("value"));
    }

}