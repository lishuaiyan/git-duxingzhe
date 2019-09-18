package com.k.multithread.chapter04;

import java.text.DecimalFormat;
import java.util.Map;

public class RecordProcessor implements StatProcessor {
    private final Map<Long, DelayItem> summaryResult;
    private static final FastTimeStampParser FAST_TIME_STAMP_PARSER = new FastTimeStampParser();
    private static final DecimalFormat df = new DecimalFormat("0000");
    private static final int INDEX_TIMESTAMP = 0;
    private static final int INDEX_TRACE_ID = 7;
    private static final int INDEX_MESSAGE_TYPE = 2;
    private static final int INDEX_OPERATION_NAME = 4;
    private static final int SRC_DEVICE = 5;
    private static final int DEST_DEVICE = 6;
    public static final int FIELDS_COUNT = 11;
    private final Map<String, DelayData> immediateResult;
}
