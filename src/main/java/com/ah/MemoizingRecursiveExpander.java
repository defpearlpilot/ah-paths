package com.ah;


import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by andrew on 5/16/15.
 */
public class MemoizingRecursiveExpander
{
    private final int depth;


    private final Splitter splitter;


    private final Map< TraversedNode, Map< TraversedNode, Long > > memoizedTraversals = Maps.newHashMap( );


    public MemoizingRecursiveExpander( int _depth )
    {
        depth = _depth;
        splitter = new Splitter( depth );
    }


    /**
     * Main entry point method to count paths from the selected character at the depth initialized
     * in the constructor
     *
     * @param ch
     * @return
     */
    public BigDecimal countPaths( Character ch )
    {
        Map< TraversedNode, Long > initialNodes = captureExpansion( splitter.getInitialBatchSize( ), new TraversedNode( ch ) );

        if ( depth == 1 )
        {
            return new BigDecimal( initialNodes.size( ) );
        }

        return countNodes( splitter.getInitialBatchSize( ), initialNodes );
    }


    private BigDecimal countNodes( int currentDepth, Map< TraversedNode, Long > nodesToExpand )
    {
        if ( currentDepth == depth )
        {
            return new BigDecimal( nodesToExpand.values( )
                                                .stream( )
                                                .mapToLong( Long::longValue )
                                                .sum( ) );
        }

        /*
        For each node, recursively get the number of nodes at the default depth and multiply that by the occurrences of that node
         */
        return nodesToExpand.entrySet( )
                            .stream( )
                            .map( entry -> {
                                TraversedNode node = entry.getKey( );
                                BigDecimal multiplier = BigDecimal.valueOf( entry.getValue( ) );

                                Map< TraversedNode, Long > pathsFromNodeAtBatch = getOrCaptureExpansion( node );

                                return multiplier.multiply( countNodes( currentDepth + splitter.getBatchSize( ), pathsFromNodeAtBatch ) );
                            } )
                            .reduce( BigDecimal.ZERO, ( accumulator, addend ) -> accumulator.add( addend ) );
    }


    /**
     * If we have memoized the node counts from a particular node for the incremental depth then return it.
     * <p>
     * Otherwise, we expand the paths and memoize and return that.
     *
     * @param node
     * @return a map of nodes to occurrences
     */
    private Map< TraversedNode, Long > getOrCaptureExpansion( TraversedNode node )
    {
        Map< TraversedNode, Long > traversedNodes = memoizedTraversals.get( node );
        if ( traversedNodes != null )
        {
            return traversedNodes;
        }

        traversedNodes = captureExpansion( splitter.getBatchSize( ), node );
        memoizedTraversals.put( node, traversedNodes );

        return traversedNodes;
    }


    /**
     * Calculate a path expansion at the specified depth from the starting point.  The method returns
     * a map from the node to the amount of times it appears in that depth.
     *
     * @param depth
     * @param startingPoint
     * @return
     */
    private Map< TraversedNode, Long > captureExpansion( int depth, TraversedNode startingPoint )
    {
        if ( startingPoint.isVowel( ) )
        {
            startingPoint.incrementVisitedCount( );
        }

        Map< TraversedNode, Long > nodes = Maps.newHashMap( );
        nodes.put( startingPoint, 1L );

        if ( depth == 0 )
        {
            return nodes;
        }

        int currentDepth = 1;
        while ( currentDepth <= depth )
        {
            /*
            At higher sequence counts, we'd have to recursively traverse millions of possible paths.  For each distinct starting point,
             the next possible paths are deterministic.  So count the occurrences which will later be used to multiply by the possible paths
             for each node

            For each node, expand to the paths under it and merge the occurrences in a map.

            Also, I couldn't figure out how to do this merge with my Java 8 experimentation.
             */
            Map< TraversedNode, Long > mergedCounts = Maps.newHashMap( );
            for ( Map.Entry< TraversedNode, Long > entry : nodes.entrySet( ) )
            {
                TraversedNode key = entry.getKey( );
                Long value = entry.getValue( );

                List< TraversedNode > expanded = expand( key );
                for ( TraversedNode node : expanded )
                {
                    if ( !mergedCounts.containsKey( node ) )
                    {
                        mergedCounts.put( node, value );
                        continue;
                    }

                    mergedCounts.put( node, mergedCounts.get( node ) + value );
                }
            }

            nodes = mergedCounts;
            ++currentDepth;
        }

        return nodes;
    }


    private List< TraversedNode > expand( TraversedNode startingPoint )
    {
        List< Character > transitions = startingPoint.canVisitVowels( ) ? Transitions.getTransitions( startingPoint.getCharacter( ) )
                : Transitions.getNVTransitions( startingPoint.getCharacter( ) );

        return transitions.stream( )
                          .map( ch -> new TraversedNode( ch, startingPoint.getVowelsVisited( ) ) )
                          .collect( Collectors.toList( ) );
    }
}
