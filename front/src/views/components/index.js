import title from "./pageTitle"
import delInformation from"./delInformation"
import nextPage from "./nextPage"

const publicTitle={
    install:function(Vue){
            Vue.component('pageTitle',title),
            Vue.component('delInformation',delInformation),
            Vue.component('nextPage',nextPage)

    }
}

export default publicTitle;
