class DummyClass_53221 {
    @Test
    public void testExtremeGroupPagination() {
        for (int i = 0; i < 502; i++) {
            ScimUser user = createUser("deleteme_" + new RandomValueStringGenerator().generate().toLowerCase(), "Passwo3d");
            scimGroups.add(createGroup("cfid_" + new RandomValueStringGenerator().generate().toLowerCase(), new ScimGroupMember(user.getId())));
        }

        ResponseEntity<Map> response = client.getForEntity(serverRunning.getUrl(groupEndpoint + "?count=502"), Map.class);

        Map results = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat((Integer) results.get("totalResults"), greaterThan(500));
        assertThat((List<?>) results.get("resources"), hasSize(500));
        assertThat(results.get("itemsPerPage"), is(500));
        assertThat(results.get("startIndex"), is(1));

    }

}