
pub fn run_int_code(code: Vec<i32>) -> i32 {
    let mut state = code.clone();
    let st_str = format!("{:?}", state);
    println!("{}", st_str);
    for idx in (0..state.len()).step_by(4) {
        let opcode = state[idx];
        let a = state[idx + 1] as usize;
        let b = state[idx + 2] as usize;
        let put_idx = state[idx + 3] as usize;
        match opcode {
            1 => state[put_idx] = state[a] + state[b],
            2 => state[put_idx] = state[a] * state[b],
            99 => break,
            c => panic!("oops wrong opcode {}", c),
        };
    }
    state[0]
}

