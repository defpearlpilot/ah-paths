package io.dpp


class Batching(val batchSize: Int,val initialBatchSize: Int)
{
  companion object
  {
    fun forSequence(depth: Int): Batching
    {
      return when {
        depth > 50 -> {
          val batchSize = 15
          val initialBatchSize = depth % batchSize
          Batching(batchSize, initialBatchSize)
        }
        depth > 30 -> {
          val batchSize = 12
          val initialBatchSize = depth % batchSize
          Batching(batchSize, initialBatchSize)
        }
        depth > 10 -> {
          val batchSize = 10
          val initialBatchSize = depth % batchSize
          Batching(batchSize, initialBatchSize)
        }
        depth <= 3 -> {
          val initialBatchSize = 1
          val batchSize = depth - initialBatchSize
          Batching(batchSize, initialBatchSize)
        }
        else -> {
          val batchSize = Math.max(2, depth / 3)
          val initialBatchSize = depth % batchSize
          Batching(batchSize, initialBatchSize)
        }
      }
    }
  }

  override fun toString(): String
  {
    return "Batching($batchSize, $initialBatchSize)"
  }

}