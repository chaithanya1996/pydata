val col_seq = train_data.columns.slice(2,5).toSeq
val df = train_data.select(col_seq.head, col_seq.tail: _*)
df.show(10)
