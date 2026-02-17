class DummyClass_33882 {
    @BeforeEach
    public void populateServer() {
        List<IBaseResource> input = new ArrayList<>();

        Patient p1 = new Patient();
        p1.addName().setFamily("PATIENT1");
        input.add(p1);

        Patient p2 = new Patient();
        p2.addName().setFamily("PATIENT2");
        input.add(p2);

        input.add(new Patient().addName(new HumanName().setFamily("PATIENT3")));

        List<IBaseResource> response = fhirClient.transaction()
                .withResources(input)
                .encodedJson()
                .execute();
        assertEquals(3, response.size());
    }

}