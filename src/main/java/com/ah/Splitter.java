package com.ah;


/**
 * This class is used for computing a reasonable cache expansion.  This is likely out of date since the latest algorithm is
 * extremely fast
 */
public class Splitter
{
    private final int initialBatchSize;


    private final int batchSize;


    public Splitter( int depth )
    {
        if ( depth >= 10 )
        {
            batchSize = 10;
            initialBatchSize = depth % batchSize;
        }
        else if ( depth <= 3 )
        {
            initialBatchSize = 1;
            batchSize = depth - initialBatchSize;
        }
        else
        {
            batchSize = depth / 3;
            initialBatchSize = depth % batchSize;
        }
    }


    public int getInitialBatchSize()
    {
        return initialBatchSize;
    }


    public int getBatchSize()
    {
        return batchSize;
    }
}
