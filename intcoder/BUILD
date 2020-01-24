load("@io_bazel_rules_rust//rust:rust.bzl", "rust_binary", "rust_library", "rust_test")

rust_library(
    name = "intcode_lib",
    srcs = [
        "src/intcoder.rs",
        "src/lib.rs",
    ],
    crate_features = [
        "default",
        "proc-macro2",
    ],
    deps = ["//intcoder/cargo:pyo3"],
)

rust_library(
    name = "intcode_pylib",
    srcs = [
        "src/intcoder.rs",
        "src/lib.rs",
    ],
    crate_features = [
        "default",
        "proc-macro2",
    ],
    crate_type = "cdylib",
    deps = ["//intcoder/cargo:pyo3"],
)

rust_binary(
    name = "hello",
    srcs = ["src/main.rs"],
    deps = ["//intcoder:intcode_lib"],
)
