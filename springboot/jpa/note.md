# 注解
## `@GeneratedValue`
为一个实体生成一个唯一标识的主键,@GeneratedValue提供了主键的生成策略。@GeneratedValue注解有两个属性strategy和generator.   
strategy  主键生成策略   
generator 主键生成器名称   
1. GenerationType.TABLE  
使用一个特定的数据库表格来保存主键,持久化引擎通过关系数据库的一张特定的表格来生成主键,这种策略的好处就是不依赖于外部环境和数据库的具体实现,在不同数据库间可以很容易的进行移植,但由于其不能充分利用数据库的特性,所以不会优先使用。
2. GenerationType.SEQUENCE   
利用序列生成主键，
3. GenerationType.IDENTITY   
主键自增长
4. GenerationType.AUTO   
把主键生成策略交给持久化引擎(persistence engine),持久化引擎会根据数据库在以上三种主键生成策略中选择其中一种。