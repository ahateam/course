
export default function getCollegeName(age){
    let courseAge

    const tableCollege=[
        {val:1,label:"大一上"},{val:2,label:"大一下"},
        {val:3,label:"大二上"},{val:4,label:"大二下"},
        {val:5,label:"大三上"},{val:6,label:"大三下"},
        {val:7,label:"大四上"},{val:8,label:"大四下"},
        {val:9,label:"毕业生"},{val:10,label:"毕业生"},
    ]
    for(let i=0;i<tableCollege.length;i++){
        if(tableCollege[i].val===age){
            courseAge= tableCollege[i].label
            break
        }
    }

    return courseAge
}
