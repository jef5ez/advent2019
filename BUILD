load("@rules_java//java:defs.bzl", "java_binary", "java_library")
load("@io_bazel_rules_scala//scala:scala.bzl", "scala_binary", "scala_library", "scala_test")

java_binary(
    name = "day1",
    srcs = glob(["day1/src/main/java/**/*.java"]),
    main_class = "jef5ez.advent.Day1",
    deps = ["//src/main/resources:advent-inputs"],
)

scala_binary(
    name = "day2",
    srcs = glob(["day2/src/main/scala/**/*.scala"]),
    main_class = "jef5ez.advent.Day2",
    deps = ["//src/main/resources:advent-inputs"],
)

scala_binary(
    name = "day3",
    srcs = glob(["day3/src/main/scala/**/*.scala"]),
    main_class = "jef5ez.advent.Day3",
    deps = ["//src/main/resources:advent-inputs"],
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
    deps = [
        "//src/main/resources:advent-inputs",
        "@maven//:org_jgrapht_jgrapht_core",
    ],
)

scala_binary(
    name = "day8",
    srcs = glob(["day8/src/main/scala/**/*.scala"]),
    main_class = "jef5ez.advent.Day8",
    deps = ["//src/main/resources:advent-inputs"],
)

scala_binary(
    name = "day10",
    srcs = glob(["day10/src/main/scala/**/*.scala"]),
    main_class = "jef5ez.advent.Day10",
    deps = ["//src/main/resources:advent-inputs"],
)
