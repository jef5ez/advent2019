"""
cargo-raze crate workspace functions

DO NOT EDIT! Replaced on runs of cargo-raze
"""
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
load("@bazel_tools//tools/build_defs/repo:git.bzl", "new_git_repository")

def _new_http_archive(name, **kwargs):
    if not native.existing_rule(name):
        http_archive(name=name, **kwargs)

def _new_git_repository(name, **kwargs):
    if not native.existing_rule(name):
        new_git_repository(name=name, **kwargs)

def raze_fetch_remote_crates():

    _new_http_archive(
        name = "raze__log__0_3_6",
        url = "https://crates-io.s3-us-west-1.amazonaws.com/crates/log/log-0.3.6.crate",
        type = "tar.gz",
        sha256 = "ab83497bf8bf4ed2a74259c1c802351fcd67a65baa86394b6ba73c36f4838054",
        strip_prefix = "log-0.3.6",
        build_file = Label("//intcoder/cargo/remote:log-0.3.6.BUILD")
    )

