load("@io_bazel_rules_rust//rust:rust.bzl", "rust_binary", "rust_library", "rust_test")

rust_library(
    name = "intcode_lib",
    srcs = [
        "src/intcoder.rs",
        "src/lib.rs",
    ],
    crate_features = ["default"],
    deps = ["//intcoder/cargo:log"],
)

rust_library(
    name = "intcode_pylib",
    srcs = [
        "src/intcoder.rs",
        "src/lib.rs",
    ],
    crate_features = ["default"],
    crate_type = "cdylib",
    deps = ["//intcoder/cargo:log"],
)

rust_binary(
    name = "hello",
    srcs = ["src/main.rs"],
    deps = ["//intcoder:intcode_lib"],
)
