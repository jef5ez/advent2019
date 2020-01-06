extern crate intcode_lib;

use intcode_lib::intcoder::run_int_code;
use std::fs;
use std::path::PathBuf;

fn main() {
    let path = "/home/joseph/src/advent2019/src/main/resources/day2.txt";

    let program: String = fs::read_to_string(path).expect("Error reading file");

    let mut ints: Vec<i32> = program.trim().split(',').map(|x| x.parse().unwrap()).collect();
    ints[1] =  12;
    ints[2] = 2;


    let end = run_int_code(ints);
    println!("part1 returned {}", end)
}
