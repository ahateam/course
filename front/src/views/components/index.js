import title from "./pageTitle"
import delInformation from"./delInformation"
import nextPage from "./nextPage"
import twoDialog from './twoDialog'
const publicTitle={
    install:function(Vue){
            Vue.component('pageTitle',title),
            Vue.component('delInformation',delInformation),
            Vue.component('nextPage',nextPage),
            Vue.component('twoDialog',twoDialog)

    }
}

export default publicTitle;
