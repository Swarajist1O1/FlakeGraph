class DummyClass_176820 {
    @Test
    public void byteArrayValueIsCheckedAsString() throws Exception {
        BasicAttributes entity = new BasicAttributes(true);
        entity.put("userPassword", new byte[]{50, 82, 115, 48, 67, 99, 54, 74});

        Whitebox.setInternalState(entityFacade, "entity", entity);

        entityFacade.entityHasAttributeWithValue("userpassword", "2Rs0Cc6J");
    }

}