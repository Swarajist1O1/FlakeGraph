class DummyClass_91428 {
@TestLogging("org.elasticsearch.xpack.watcher:DEBUG,org.elasticsearch.xpack.watcher.WatcherIndexingListener:TRACE")
    public void testActionConditionWithHardFailures() throws Exception {
        final String id = "testActionConditionWithHardFailures";

        final ExecutableCondition scriptConditionFailsHard = mockScriptCondition("throw new IllegalStateException('failed');");
        final List<ExecutableCondition> actionConditionsWithFailure =
                Arrays.asList(scriptConditionFailsHard, conditionPasses, InternalAlwaysCondition.INSTANCE);

        Collections.shuffle(actionConditionsWithFailure, random());

        final int failedIndex = actionConditionsWithFailure.indexOf(scriptConditionFailsHard);

        putAndTriggerWatch(id, input, actionConditionsWithFailure.toArray(new Condition[actionConditionsWithFailure.size()]));

        flush();

        assertWatchWithMinimumActionsCount(id, ExecutionState.EXECUTED, 1);

        // only one action should have failed via condition
        final SearchResponse response = searchHistory(SearchSourceBuilder.searchSource().query(termQuery("watch_id", id)));
        assertThat(response.getHits().getTotalHits(), is(1L));

        final SearchHit hit = response.getHits().getAt(0);
        final List<Object> actions = getActionsFromHit(hit.getSourceAsMap());

        for (int i = 0; i < actionConditionsWithFailure.size(); ++i) {
            final Map<String, Object> action = (Map<String, Object>)actions.get(i);
            final Map<String, Object> condition = (Map<String, Object>)action.get("condition");
            final Map<String, Object> logging = (Map<String, Object>)action.get("logging");

            assertThat(action.get("id"), is("action" + i));

            if (i == failedIndex) {
                assertThat(action.get("status"), is("condition_failed"));
                assertThat(action.get("reason"), is("condition failed. skipping: [expected] failed hard"));
                assertThat(condition, nullValue());
                assertThat(logging, nullValue());
            } else {
                assertThat(condition.get("type"), is(actionConditionsWithFailure.get(i).type()));

                assertThat(action.get("status"), is("success"));
                assertThat(condition.get("met"), is(true));
                assertThat(action.get("reason"), nullValue());
                assertThat(logging.get("logged_text"), is(Integer.toString(i)));
            }
        }
    }

}