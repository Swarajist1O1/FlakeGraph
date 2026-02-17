class DummyClass_33657 {
        //System.out.println("@@@@@Test Pretty");
        final String user = target("user").queryParam("pretty", "true").request().accept("application/json").get(String.class);
        // {"createdOn":1412036891919,"id":12345,"name":"smallnest"}]
        assertTrue(user.indexOf("createdOn") > 0);
        assertTrue(user.indexOf("\"id\":12345") > 0);
        assertTrue(user.indexOf("\"name\":\"smallnest\"") > 0);
        //response does not contain a return character
        //assertTrue(user.indexOf("\n\t") > 0);

    }

}