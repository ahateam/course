import store from './store'
export default function getCollegeName(collegeId){
    let collegeName
    const tableCollege=store.state.tableCollege
    for(let i=0;i<tableCollege.length;i++){
        if(tableCollege[i].collegeId===collegeId){
            collegeName= tableCollege[i].collegeName
            break
        }
    }
    // tableCollege.forEach((val,index,arr)=>{
    //     //console.log(typeof collegeId)
    //     if(val.collegeId.toString()===collegeId){
    //         //console.log(55)
    //         //console.log(val.collegeName)
    //         collegeName= val.collegeName
    //        // break
    //     }
    // })
    return collegeName
}


// function f(collegeId){
//     tableCollege.map((val,index,arr)=>{
//         if(val.collegeId===collegeId){
//             console.log(55)
//             console.log(val.collegeName)
//             return val.collegeName
//         }
//
//     })
// }

