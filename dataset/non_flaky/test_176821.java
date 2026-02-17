class DummyClass_176821 {
    @Test
    public void charArrayValueIsCheckedAsString() throws Exception {
        BasicAttributes entity = new BasicAttributes(true);
        entity.put("userPassword", new char[]{'h', 'e', 'l', 'l', 'o'});

        Whitebox.setInternalState(entityFacade, "entity", entity);

        entityFacade.entityHasAttributeWithValue("userpassword", "hello");
    }

}