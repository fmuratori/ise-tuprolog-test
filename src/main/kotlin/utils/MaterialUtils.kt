package utils

import mui.material.*

// TODO: Remove when it will be implemented in MUI wrappers
inline var GridProps.xs: Int
    get() = TODO("Prop is write-only!")
    set(value) {
        asDynamic().xs = value
    }

inline var TextFieldProps.InputProps: InputBaseProps
    get() = TODO("Prop is write-only!")
    set(value) {
        asDynamic().InputProps = value
    }
