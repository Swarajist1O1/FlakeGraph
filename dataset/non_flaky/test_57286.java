class DummyClass_57286 {
  @Test
  public void testGetReplicaHistoryForContainer() throws IOException {
    // Add container history for container id 1
    final UUID u1 = newDatanode("host1", "127.0.0.1");
    final UUID u2 = newDatanode("host2", "127.0.0.2");
    final UUID u3 = newDatanode("host3", "127.0.0.3");
    final UUID u4 = newDatanode("host4", "127.0.0.4");
    reconContainerManager.upsertContainerHistory(1L, u1, 1L, 1L);
    reconContainerManager.upsertContainerHistory(1L, u2, 2L, 1L);
    reconContainerManager.upsertContainerHistory(1L, u3, 3L, 1L);
    reconContainerManager.upsertContainerHistory(1L, u4, 4L, 1L);

    reconContainerManager.upsertContainerHistory(1L, u1, 5L, 1L);

    Response response = containerEndpoint.getReplicaHistoryForContainer(1L);
    List<ContainerHistory> histories =
        (List<ContainerHistory>) response.getEntity();
    Set<String> datanodes = Collections.unmodifiableSet(
        new HashSet<>(Arrays.asList(
            u1.toString(), u2.toString(), u3.toString(), u4.toString())));
    Assert.assertEquals(4, histories.size());
    histories.forEach(history -> {
      Assert.assertTrue(datanodes.contains(history.getDatanodeUuid()));
      if (history.getDatanodeUuid().equals(u1.toString())) {
        Assert.assertEquals("host1", history.getDatanodeHost());
        Assert.assertEquals(1L, history.getFirstSeenTime());
        Assert.assertEquals(5L, history.getLastSeenTime());
      }
    });

    // Check getLatestContainerHistory
    List<ContainerHistory> hist1 = reconContainerManager
        .getLatestContainerHistory(1L, 10);
    Assert.assertTrue(hist1.size() <= 10);
    // Descending order by last report timestamp
    for (int i = 0; i < hist1.size() - 1; i++) {
      Assert.assertTrue(hist1.get(i).getLastSeenTime()
          >= hist1.get(i + 1).getLastSeenTime());
    }
  }

}