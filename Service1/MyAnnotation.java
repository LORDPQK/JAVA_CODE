package Service1;

public @interface MyAnnotation {
//属性（类似方法）
    String name() default "松吹君";
    int age() default 20;

}
