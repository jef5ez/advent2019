load("@rules_java//java:defs.bzl", "java_binary")
load("@io_bazel_rules_scala//scala:scala.bzl", "scala_binary", "scala_library", "scala_test")

java_binary(
    name = "day1",
    srcs = glob(["day1/src/main/java/**/*.java"]),
    main_class = "jef5ez.advent.Day1",
    resources = glob(["day1/src/main/resources/*"]),
)

scala_binary(
    name = "day2",
    srcs = glob(["day2/src/main/scala/**/*.scala"]),
    main_class = "jef5ez.advent.Day2",
    resources = glob(["day2/src/main/resources/*"]),
)

scala_binary(
    name = "day3",
    srcs = glob(["day3/src/main/scala/**/*.scala"]),
    main_class = "jef5ez.advent.Day3",
    resources = glob(["day3/src/main/resources/*"]),
)

scala_binary(
    name = "day4",
    srcs = glob(["day4/src/main/scala/**/*.scala"]),
    main_class = "jef5ez.advent.Day4",
)

scala_binary(
    name = "day6",
    srcs = glob(["day6/src/main/scala/**/*.scala"]),
    main_class = "jef5ez.advent.Day6",
    resources = glob(["day6/src/main/resources/*"]),
    deps = ["@maven//:org_jgrapht_jgrapht_core"],
)
