import Vue from 'vue'
import Vuex from 'vuex'
import da from "element-ui/src/locale/lang/da";
import {getRequest} from "../utils/api";

Vue.use(Vuex);

const now = new Date();

const store = new Vuex.Store({
    state: {
        routes: [],
        sessions:[
            {
                id:1,
                user:{
                    name:'示例介绍',
                    img:'https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1141259048,554497535&fm=26&gp=0.jpg'
                },
                messages:[{
                    content:'Hello，这是一个基于Vue + Vuex + Webpack构建的简单chat示例，聊天记录保存在localStorge, 有什么问题可以通过Github Issue问我。',
                    date:now
                },{
                    content:'项目地址(原作者): https://github.com/coffcer/vue-chat',
                    date:now
                },{
                    content:'本项目地址(重构): https://github.com/is-liyiwei',
                    date:now
                }]
            },{
                id:2,
                user:{
                    name:'webpack',
                    img:'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596366546289&di=f84ec1f118cff77c47b987d171e77c3b&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F47%2F66%2F01300000337727123266663353910.jpg'
                },
                messages:[{
                    content:'Hi，我是webpack哦',
                    date:now
                }]
            }
        ],
        hrs:[],
        currentSessionId:1,
        filterKey:''
    },
    mutations: {
        initRoutes(state, data) {
            state.routes = data;
        },
        changeCurrentSessionId (state,id) {
            state.currentSessionId = id;
        },
        addMessage (state,msg) {
            state.sessions[state.currentSessionId-1].messages.push({
                content:msg,
                date: new Date(),
                self:true
            })
        },
        INIT_DATA (state) {
            let data = localStorage.getItem('vue-chat-session');
            if (data) {
                state.sessions = JSON.parse(data);
            }
        },
        INIT_HR (state,hrs) {
            state.hrs = hrs;
        }
    },
    actions: {
        initData (context) {
            context.commit('INIT_DATA')
            getRequest("/chat/otherHrs").then(resp=>{
                if(resp){
                    context.commit('INIT_HR',resp);
                }
            })
        }
    }
})

store.watch(function (state) {
    return state.sessions
},function (val) {
    console.log('CHANGE: ', val);
    localStorage.setItem('vue-chat-session', JSON.stringify(val));
},{
    deep:true/*这个貌似是开启watch监测的判断,官方说明也比较模糊*/
})

export default store;