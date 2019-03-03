window.comUtils = {
    currentTime:function () {
        let d = new Date(), str = '';
        str += d.getFullYear() + '年';
        str += d.getMonth() + 1 + '月';
        str += d.getDate() + '日';
        str += d.getHours() + '时';
        str += d.getMinutes() + '分';
        str += d.getSeconds() + '秒';
        return str;
    },
    fullScreen:function () {
        let el = document.documentElement;
        let rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullscreen;
        if (typeof rfs !== "undefined" && rfs) {
            rfs.call(el);
        }
    },
    //拷贝对象
    cloneData: function (oVal, newVal) {
        if (!newVal) {
            //判断旧值是数组还是对象
            if (oVal === null) {
                return null;
            }
            if (oVal instanceof Array) {
                newVal = [];
            } else {
                newVal = {};
            }
        }
        for (let dataIndex in oVal) {
            //如果内部值仍为对象则地递归调用
            if (typeof oVal[dataIndex] === 'object') {
                newVal[dataIndex] = this.cloneData(oVal[dataIndex]);
            } else {
                newVal[dataIndex] = oVal[dataIndex];
            }
        }
        return newVal;
    },
};