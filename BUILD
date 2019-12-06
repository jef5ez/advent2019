load("@rules_java//java:defs.bzl", "java_binary")
load("@io_bazel_rules_scala//scala:scala.bzl", "scala_binary", "scala_library", "scala_test")

java_binary(
    name = "day1",
    srcs = glob(["day1/src/main/java/jef5ez/advent/*.java"]),
    main_class = "jef5ez.advent.Day1",
    resources = glob(["day1/src/main/resources/*"]),
)

scala_binary(
    name = "day2",
    srcs = glob(["day2/src/main/scala/jef5ez/advent/*.scala"]),
    main_class = "jef5ez.advent.Day2",
    resources = glob(["day2/src/main/resources/*"]),
)
