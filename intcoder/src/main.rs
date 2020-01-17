extern crate intcode_lib;

use intcode_lib::intcoder::run_int_code;

fn main() {
    let v = vec![1, 2, 3];

    run_int_code(v);
}
