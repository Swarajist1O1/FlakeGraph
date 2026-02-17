class DummyClass_176823 {
    @Test
    public void intArrayValueIsCheckedAsString() throws Exception {
        BasicAttributes entity = new BasicAttributes(true);
        entity.put("userPassword", new int[]{1, 2, 3});

        Whitebox.setInternalState(entityFacade, "entity", entity);

        entityFacade.entityHasAttributeWithValue("userpassword", "{1,2,3}");
    }

}