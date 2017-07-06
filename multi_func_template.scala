def sin_col_one_hot (one_df: org.apache.spark.sql.DataFrame,one_col: String): 
 org.apache.spark.sql.DataFrame = {
   
import org.apache.spark.ml.feature.{OneHotEncoder, StringIndexer}
   
val indexer = new StringIndexer()
  .setInputCol(one_col)
  .setOutputCol(s"${one_col}_Index")
  
val indexed = indexer.fit(one_df).transform(one_df)

val encoder = new OneHotEncoder()
  .setInputCol(s"${one_col}_Index")
  .setOutputCol(s"${one_col}_Vector")

val encoded = encoder.transform(indexed).drop(s"${one_col}_Index")
return(encoded)
}
def mul_col_one_hot(input_data: org.apache.spark.sql.DataFrame,arr_cols: Array[String]):org.apache.spark.sql.DataFrame = {
  
  if(arr_cols.size == 1){
    val final_df = sin_col_one_hot(input_data,arr_cols(0))
    return(final_df)
  }
  else{
    val inter_df = sin_col_one_hot(input_data,arr_cols(0))
    val size_arr = arr_cols.size
    val fur_arr_input = arr_cols.slice(1,arr_cols.size)
    val further_process_df = mul_col_one_hot(inter_df,fur_arr_input)
    return(further_process_df)
  }
}
