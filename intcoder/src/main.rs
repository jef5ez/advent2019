
fn run_int_code(code: Vec<i32>) {
    println!("the slice has {} elements", code.len());
}

fn main() {
    let v = vec![1, 2, 3];

    run_int_code(v);
}
