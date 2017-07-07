// --------- Multi Column selection snippet ------------- //
val col_seq = train_data.columns.slice(2,5).toSeq
val df = train_data.select(col_seq.head, col_seq.tail: _*)
df.show(10)

// ------------- Dealing with nulls in col creation ---------//
val actualDf = sourceDf.withColumn(
  "is_even",
  when(
    col("number").isNotNull, 
    isEvenSimpleUdf(col("number"))
  ).otherwise(lit(null))
)

actualDf.show()
// --------  null value finding --------- //
val col_name = "Cabin"
titanic.where(s"${col_name} is null").count
