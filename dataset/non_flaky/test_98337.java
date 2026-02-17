class DummyClass_98337 {
    @BeforeEach
    public void setup() {
        this.unirestInstance = Unirest.spawnInstance();
        this.unirestInstance.config().interceptor(interceptor);
    }

}