class DummyClass_136487 {
    @Test
    public void should_update_udt_fields() throws Exception {
        //Given
        Long id = RandomUtils.nextLong(0L, Long.MAX_VALUE);
        scriptExecutor.executeScriptTemplate("EntityWithNonFrozenUDT/insertRow.cql", ImmutableMap.of("id", id));

        //When
        manager
                .dsl()
                .update()
                .fromBaseTable()
                .nonFrozen().val().Set("new_value")
                .nonFrozen().li().Set(Arrays.asList("3"))
                .nonFrozen().se().Set_FromJSON("[\"3\"]")
                .nonFrozen().ma().Set(ImmutableMap.of(3, "3"))
                .nonFrozen().address().Set_FromJSON("{\"street\": \"new_street\", \"number\": 100}")
                .where()
                .id().Eq(id)
                .execute();

        //Then
        final Row actual = session.execute("SELECT * FROM it_3_6.non_frozen_udt WHERE id = " + id).one();

        assertThat(actual).isNotNull();

        final UDTValue nonFrozen = actual.getUDTValue("nonFrozen");

        assertThat(nonFrozen).isNotNull();
        assertThat(nonFrozen.getList("li", String.class)).containsExactly("3");
        assertThat(nonFrozen.getSet("se", String.class)).containsExactly("3");
        assertThat(nonFrozen.getMap("ma", Integer.class, String.class)).hasSize(1);
        assertThat(nonFrozen.getMap("ma", Integer.class, String.class)).containsEntry(3, "3");

        final UDTValue address = nonFrozen.getUDTValue("address");

        assertThat(address).isNotNull();
        assertThat(address.getString("street")).isEqualTo("new_street");
        assertThat(address.getInt("number")).isEqualTo(100);
    }

}