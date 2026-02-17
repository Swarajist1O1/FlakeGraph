class DummyClass_176822 {
    @Test
    public void stringValueIsCheckedAsString() throws Exception {
        BasicAttributes entity = new BasicAttributes(true);
        entity.put("userPassword", "hello");

        Whitebox.setInternalState(entityFacade, "entity", entity);

        entityFacade.entityHasAttributeWithValue("userpassword", "hello");
    }

}