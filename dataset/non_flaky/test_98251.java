class DummyClass_98251 {
    @Test
    public void testFindUserWithSpecialCharIdByPrincipalName2() throws RepositoryException {
        List<String> ids = Arrays.asList("]");
        for (String id : ids) {
            User user = null;
            try {
                user = userMgr.createUser(id, "pw");
                superuser.save();

                boolean found = false;
                Iterator<Authorizable> it = userMgr.findAuthorizables("rep:principalName", id, UserManager.SEARCH_TYPE_USER);
                while (it.hasNext() && !found) {
                    Authorizable a = it.next();
                    found = id.equals(a.getID());
                }
                assertTrue(found);
            } finally {
                if (user != null) {
                    user.remove();
                    superuser.save();
                }
            }
        }
    }

}